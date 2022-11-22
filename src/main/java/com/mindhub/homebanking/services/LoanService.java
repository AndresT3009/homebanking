package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Loan;
import java.util.List;

public interface LoanService {

    public List<LoanDTO> getloansDTO();

    public Loan findById(long id);

    public void saveLoan(Loan loan);
}
