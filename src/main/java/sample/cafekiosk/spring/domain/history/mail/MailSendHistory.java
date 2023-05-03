package sample.cafekiosk.spring.domain.history.mail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MailSendHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromEmail;
    private String toEmail;
    private String suject;
    private String content;

    @Builder
    private MailSendHistory(Long id, String fromEmail, String toEmail, String suject, String content) {
        this.id = id;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
        this.suject = suject;
        this.content = content;
    }
}
