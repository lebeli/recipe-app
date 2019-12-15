package com.hdmstuttgart.fluffybear.repository;


import com.hdmstuttgart.fluffybear.model.Instruction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface InstructionRepository extends CrudRepository<Instruction, Long> {

    Instruction findById(long id);
    Set<Instruction> findByInstruction(String instruction);
}
