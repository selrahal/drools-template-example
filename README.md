#Drools Template Example

Drools templates are used to provide a complex rule structure and fill in parameterized values with data. This allows separation between rule structure and data for data-driven rule sets. For example, one might want to set a maximum amount for a Loan that is dependent on the type of loan. The rules would look like such:


```
rule "Set max amount for simple loan"
when
	loan : Loan(identifier == "simple", maxAmount != 1000)
then
	modify (loan) {
		setMaxAmount(1000)
	}
end


rule "Set max amount for complex loan"
when
	loan : Loan(identifier == "complex", maxAmount != 5000)
then
	modify (loan) {
		setMaxAmount(5000)
	}
end

```

This can work for one, two, maybe even twenty rules but becomes unwieldy and burdensome to maintain as the number of rules grow. The data can be stored in a table:

| Identifier | Max Loan Amount|
|------------|----------------|
|simple|1000|
|complex|5000|


And we can model the rule by parameterizing the data-driven portions like so:

```
rule "Set max amount from identifier @{row.rowNumber}"
when
	loan : Loan(identifier == "@{identifier}", maxAmount != @{maxAmount})
then
	modify (loan) {
		setMaxAmount(@{maxAmount})
	}
end
```

This project contains a working drools template. Use maven to verify the functionality like so:

```shell
mvn clean test
```

##Requirements

This project has a dependency on version `6.3.0.Final-redhat-5` of Drools which is provided as BxMS v6.2. You can download the maven repository at the [Red Hat Customer Portal](https://access.redhat.com)
