/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acme.acmepools.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author Juneau
 */
@Converter
public class LocalToZonedConverter implements AttributeConverter<ZonedDateTime, LocalDateTime> {
    @Override
    public LocalDateTime convertToDatabaseColumn(ZonedDateTime entityValue) {
        return entityValue.toLocalDateTime();
    }

    @Override
    public ZonedDateTime convertToEntityAttribute(LocalDateTime databaseValue) {
        return ZonedDateTime.of(databaseValue, ZoneId.systemDefault());
    }
}
