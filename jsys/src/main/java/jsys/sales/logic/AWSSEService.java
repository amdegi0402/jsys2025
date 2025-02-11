

package jsys.sales.logic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AWSSEService {
	private final AmazonSimpleEmailService sesClient;

	public AWSSEService() {
		 // finalフィールドを確実に初期化するため、ローカル変数を使用
        AmazonSimpleEmailService client = null;
		String configPath = "C:/pleiades/workspace_javasys/configFiles/config.properties";
		// ↑実際のパスに合わせて修正してください
		Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream(configPath)) {
		    props.load(fis);
		    BasicAWSCredentials awsCreds = new BasicAWSCredentials(
		            props.getProperty("aws.accessKeyId"),
		            props.getProperty("aws.secretKey")
		        );
		    // 他のプロパティも取得可
		    client = AmazonSimpleEmailServiceClientBuilder.standard() //標準的なメール配信サービスを選ぶ
			        .withRegion(Regions.AP_NORTHEAST_1)  // Regions を使用（AP_NORTHEAST_1は「東京」を意味する）
			        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))//認証？これがないとメール送信できない
			        .build();//上記の内容でビルド
		} catch (IOException e) {
		    e.printStackTrace();
		}
		 // try-catchブロックの外でfinalフィールドを初期化
	    this.sesClient = client;
	}

	//otp生成
	public String generateOTP() {
		return String .format("%06d", new Random().nextInt(999999)); //認証用の6桁の数字を生成
	}

	//メール送信
	public void sendOTP(String toEmail, String otp) {
	    try {
	        SendEmailRequest request = new SendEmailRequest()
	        	//宛先の設定
	            .withDestination(new Destination().withToAddresses(toEmail))
	            //メールの内容を作成
	            .withMessage(new Message()
	            	//本文
	                .withBody(new Body()
	                    .withText(new Content()
	                        .withCharset("UTF-8")//日本語に対応させる
	                        .withData("認証コード: " + otp))) //メッセージの内容
	                .withSubject(new Content()
	                    .withCharset("UTF-8")//日本語に対応させる
	                    .withData("認証コード")))//件名
	            //差出人のアドレスを設定
	            .withSource("feeling-respect@waltz.ocn.ne.jp");
	        //メール送信
	        sesClient.sendEmail(request);
	    } catch (AmazonSimpleEmailServiceException e) {
	        throw new RuntimeException("メール送信失敗", e);
	    }
	}

}
