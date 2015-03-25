package com.github.asufana.typetalk.resources;

import org.apache.commons.lang3.*;
import org.joda.time.*;
import org.joda.time.format.*;

public abstract class AbstractResource {
    
    protected DateTime toDateTime(final String isoDateTimeFormatString) {
        if (StringUtils.isEmpty(isoDateTimeFormatString)) {
            return null;
        }
        return ISODateTimeFormat.dateTimeNoMillis()
                                .parseDateTime(isoDateTimeFormatString);
    }
}
