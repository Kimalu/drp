package com.Kimalu.drp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@DiscriminatorValue("clientLevel")
public class ClientLevel extends AbstractDataDict {

}
