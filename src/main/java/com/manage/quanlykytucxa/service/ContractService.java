package com.manage.quanlykytucxa.service;

import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Contract;
import com.manage.quanlykytucxa.repository.ContractRepository;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id " + id));

    }

    public Contract updateContract(Contract contract) {
        return contractRepository.save(contract);
    }
}
