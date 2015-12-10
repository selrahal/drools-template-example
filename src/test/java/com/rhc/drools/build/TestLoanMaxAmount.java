package com.rhc.drools.build;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.rhc.drools.model.Loan;

public class TestLoanMaxAmount {
	private KieContainer kieContainer;
	private KieBase kieBase;
	private static final String KIE_BASE = "loanKieBase";
	
	@Before
	public void init() {
		kieContainer = KieServices.Factory.get().getKieClasspathContainer();
		kieBase = kieContainer.getKieBase(KIE_BASE);
	}

	@Test
	public void testSimpleLoan() {
		Loan loan = new Loan();
		loan.setIdentifier("simple");
		KieSession kieSession = kieBase.newKieSession();
		
		kieSession.insert(loan);
		kieSession.fireAllRules();
		
		Assert.assertEquals((Integer) 1000, loan.getMaxAmount());
	}
	
	@Test
	public void testComplicatedLoan() {
		Loan loan = new Loan();
		loan.setIdentifier("complex");
		KieSession kieSession = kieBase.newKieSession();
		
		kieSession.insert(loan);
		kieSession.fireAllRules();
		
		Assert.assertEquals((Integer) 5000, loan.getMaxAmount());
	}
}