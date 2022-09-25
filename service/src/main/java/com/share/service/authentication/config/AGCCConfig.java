package com.share.service.authentication.config;

import com.huawei.agconnect.server.commons.AGCClient;
import com.huawei.agconnect.server.commons.AGCParameter;
import com.huawei.agconnect.server.commons.credential.CredentialParser;
import com.huawei.agconnect.server.commons.credential.CredentialService;
import com.huawei.agconnect.server.commons.exception.AGCException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

@Configuration
@Slf4j
public class AGCCConfig {

    @PostConstruct
    public void loadConfig() {
        try {
            File confFile = ResourceUtils.getFile("classpath:hw/agc-apiclient-949797172793068864-7127484426253217747.json");
            log.info("init acc conf path:{}",confFile.getAbsolutePath());
            log.info("file exits: {}",confFile.exists());
            CredentialService credential = CredentialParser.toCredential(confFile);
            AGCParameter parameter = AGCParameter.builder().setCredential(credential).build();
            AGCClient.initialize(parameter);
        } catch (Exception e) {
            log.error("load config have error",e);
        }

    }
}
