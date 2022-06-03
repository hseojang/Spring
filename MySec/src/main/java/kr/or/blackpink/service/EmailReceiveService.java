package kr.or.blackpink.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;


import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailReceiveService {
	private String saveDirectory;

	/**
	 * 첨부파일이 저장될 위치 설정
	 * 
	 * @param dir
	 */
	public void setSaveDirectory(String dir) {
		this.saveDirectory = dir;
	}

	/**
	 * Google gmail에 접근하여 지정한 기간 내에 모든 메일 가져오기
	 * 
	 * @param userName
	 * @param password
	 * @param startDate
	 * @param endDate
	 * @throws MessagingException
	 */
	public void receiveMailAttachedFile(String userName, String password, Date startDate, Date endDate)
			throws MessagingException {
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect("imap.gmail.com", userName, password);
			log.info("로그인 실행됨");

			// 받은편지함을 INBOX 라고 한다.
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, endDate);
			SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, startDate);
			SearchTerm andTerm = new AndTerm(newerThan, olderThan);

			// 받은 편지함에 있는 메일 모두 읽어오기
			// Message[] arrayMessages = inbox.getMessages();
			
			// 검색한 기간에 해당하는 메일 읽어오기
			Message[] arrayMessages = inbox.search(andTerm);
			for (int i = arrayMessages.length; i > 0; i--) {
				Message msg = arrayMessages[i - 1];
				Address[] fromAddress = msg.getFrom();
				// 메일 내용 변수에 담기
				String from = fromAddress[0].toString();
				String subject = msg.getSubject();
				String sentDate = msg.getSentDate().toString();
				String receivedDate = msg.getReceivedDate().toString();
				// String.contains는 대소문자를 구별하므로 모두 소문자로 처리
				String contentType = msg.getContentType().toLowerCase();
				String messageContent = "";
				String attachFiles = "";

				// 첨부파일
				if (contentType.contains("multipart")) {
					log.info("멀티파트 컨텐츠 있음");
					Multipart multiPart = (Multipart) msg.getContent();
					int numberOfParts = multiPart.getCount();
					for (int partCount = 0; partCount < numberOfParts; partCount++) {
						MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
						if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
							// 첨부파일 있을 경우 지정 폴더로 저장
							String fileName = part.getFileName();
							attachFiles += fileName + ", ";
							part.saveFile(saveDirectory + File.separator + fileName);
						} else {
							// 메일 내용 저장
							messageContent = part.getContent().toString();
						}
					}
					if (attachFiles.length() > 1) {
						attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
					}
				} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
					log.info("멀티파트 컨텐츠 없음");
					Object content = msg.getContent();
					if (content != null) {
						log.info("내용이 널이 아님!");
						messageContent = content.toString();
					}
					log.info("널 검증이 끝남");
				} else {
					log.info("멀티파트 컨텐츠도 아니고 텍스트 플레인도 아닌 경우");
					log.info(contentType);
				}

				// 읽어온 메일 콘솔창 출력
				System.out.println("Message #" + (i + 1) + ":");
				System.out.println("\t From: " + from);
				System.out.println("\t Subject: " + subject);
				System.out.println("\t Received: " + sentDate);
				System.out.println("\t Message: " + messageContent);
				System.out.println("\t Attachments: " + attachFiles);
			}

			// disconnect
			inbox.close(false);
			store.close();
			log.info("연결 종료됨");

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	

}