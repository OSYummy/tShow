package com.wisedu.core.user;

import java.io.Serializable;

public class Perm implements Serializable {
    private static final long serialVersionUID = -53588513738727763L;

    private Long id;
    private String object;
    private Integer operation;
    private Integer scope;
}
