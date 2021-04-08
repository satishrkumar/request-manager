package net.pay.you.back.request.manager.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "aws")
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AwsS3Config {
    private String accesskey;
    private String secretkey;
    private String bucketpath;
}
