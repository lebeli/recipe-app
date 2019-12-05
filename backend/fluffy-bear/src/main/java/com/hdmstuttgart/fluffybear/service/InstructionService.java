package com.hdmstuttgart.fluffybear.service;

import com.hdmstuttgart.fluffybear.model.Instruction;
import com.hdmstuttgart.fluffybear.repository.InstructionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InstructionService {
    @Autowired
    private InstructionRepository instructionRepository;

    public Set<Instruction> getAllInstruction() {
        Set<Instruction> instructions = new HashSet<>();
        instructionRepository.findAll().forEach(instruction -> {
            instructions.add(instruction);
        });
        return instructions;
    }

    public Instruction getInstruction(long id) {
        return instructionRepository.findById(id);
    }

    public Set<Instruction> getInstruction(String instruction) {
        return instructionRepository.findByInstruction(instruction);
    }

    public void addInstruction(Instruction ingredient) {
        instructionRepository.save(ingredient);
    }

    public void updateInstruction(long id, Instruction instruction) {
        instructionRepository.save(instruction); // if id already exists, Spring updates the id with the passed recipe instance
    }

    public void deleteInstruction(long id) {
        instructionRepository.deleteById(id); // if id already exists, Spring updates the id with the passed recipe instance
    }

    public void deleteAll() {
        instructionRepository.deleteAll();
    }
}
