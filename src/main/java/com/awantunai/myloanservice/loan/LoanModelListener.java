package com.awantunai.myloanservice.loan;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.awantunai.myloanservice.SequenceGeneratorService;
import com.awantunai.myloanservice.loan.entity.Loan;


@Component
public class LoanModelListener extends AbstractMongoEventListener<Loan> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public LoanModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Loan> event) {
        if (event.getSource().getId() < 1) {
            event.getSource().setId(sequenceGenerator.generateSequence(Loan.SEQUENCE_NAME));
        }
    }


}