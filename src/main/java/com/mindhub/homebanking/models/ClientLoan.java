package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="loan_id")
    private Loan loan;

    private Double amount;
    private Integer payments;

    public ClientLoan(){
    }

    public ClientLoan(Client client, Loan loan, Double amount, int payments) {
        this.id = id;
        this.client = client;
        this.loan = loan;
        this.amount = amount;
        this.payments = payments;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {this.amount = amount;}

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }


    @Override
    public String toString() {
        return "ClientLoan{" +
                "id=" + id +
                ", client=" + client +
                ", loan=" + loan +
                ", amount=" + amount +
                ", payments=" + payments +
                '}';
    }
}
