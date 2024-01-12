package com.example.aftas.web.rest;


import com.example.aftas.domain.Member;
import com.example.aftas.dto.request.MemberRequestDTO;
import com.example.aftas.dto.response.MemberResponseDTO;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/members")
public class MemberRest {
    private final MemberService memberService;
    public MemberRest(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers() {
        List<Member> members = memberService.findAll();
        List<MemberResponseDTO> memberDTOs = members.stream()
                .map(MemberResponseDTO::fromMember)
                .collect(Collectors.toList());

        return ResponseEntity.ok(memberDTOs);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseMessage> addMember(@Valid @RequestBody MemberRequestDTO memberDTO) {
        Member savedMember = memberService.save(memberDTO.toMember());

        if (savedMember == null) {
            return ResponseMessage.badRequest("Member not created");
        } else {
            return ResponseMessage.created("Member created successfully", savedMember);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable Long id) {
        Optional<Member> member = memberService.findById(id);

        if (member.isEmpty()) {
            return ResponseMessage.notFound("Member not found with ID: " + id);
        }

        MemberResponseDTO memberDTO = MemberResponseDTO.fromMember(member.get());
        return ResponseEntity.ok(memberDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMember(@PathVariable Long id, @Valid @RequestBody MemberRequestDTO memberDTO) {
        Optional<Member> existingMember = memberService.findById(id);

        if (existingMember.isEmpty()) {
            return ResponseMessage.notFound("Member not found with ID: " + id);
        }

        Member updatedMember = memberDTO.toMember();
        updatedMember.setId(id);

        Member result = memberService.update(updatedMember, id);

        if (result == null) {
            return ResponseMessage.badRequest("Failed to update member with ID: " + id);
        }

        MemberResponseDTO updatedMemberDTO = MemberResponseDTO.fromMember(result);
        return ResponseMessage.ok("Member updated successfully with ID: " + id, updatedMemberDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        Optional<Member> existingMember = memberService.findById(id);

        if (existingMember.isEmpty()) {
            return ResponseMessage.notFound("Member not found with ID: " + id);
        }

        memberService.delete(id);

        return ResponseMessage.ok("Member deleted successfully with ID: " + id, null);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String familyName) {
        List<Member> members = memberService.searchMembers(id, name, familyName);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/by-competition/{competitionId}")
    public ResponseEntity<List<MemberResponseDTO>> getMembersByCompetition(@PathVariable Long competitionId) {
        List<Member> members = memberService.getMembersByCompetition(competitionId);

        List<MemberResponseDTO> memberResponseDTOs = members.stream()
                .map(MemberResponseDTO::fromMember)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(memberResponseDTOs);
    }
}

