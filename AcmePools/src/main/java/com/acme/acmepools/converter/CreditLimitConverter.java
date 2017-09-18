/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.converter;

import com.acme.acmepools.utility.CreditLimitEncryptor;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Juneau
 */
@Converter
public class CreditLimitConverter implements AttributeConverter<BigDecimal, BigDecimal> {
    
    @Inject
    CreditLimitEncryptor encryptor;
    
    @Override
    public BigDecimal convertToDatabaseColumn(BigDecimal entityValue) {
        String encryptedFormat = encryptor.base64encode(entityValue.toString());
        return BigDecimal.valueOf(Long.valueOf(encryptedFormat));
    }

    @Override
    public BigDecimal convertToEntityAttribute(BigDecimal databaseValue) {
        String decryptedFormat = encryptor.base64decode(databaseValue.toString());
        return BigDecimal.valueOf(Long.valueOf(decryptedFormat));
    }
}
