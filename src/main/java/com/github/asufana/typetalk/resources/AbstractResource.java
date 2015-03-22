package com.github.asufana.typetalk.resources;

import org.joda.time.*;
import org.joda.time.format.*;

public abstract class AbstractResource {
    
    protected DateTime toDateTime(final String isoDateTimeFormatString) {
        return ISODateTimeFormat.dateTime()
                                .parseDateTime(isoDateTimeFormatString);
    }
    
}
