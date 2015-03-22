package com.github.asufana.typetalk.resources;

import lombok.*;

@Value
@EqualsAndHashCode(callSuper = true)
public class Profile extends AbstractResource {
    private Account account;
}
