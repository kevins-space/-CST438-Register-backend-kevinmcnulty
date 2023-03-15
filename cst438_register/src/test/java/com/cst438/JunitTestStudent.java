package com.cst438;

import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;
import com.cst438.domain.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class JunitTestStudent {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testAddStudent() throws Exception {
        when(studentRepository.findByEmail(anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                .param("email", "tim.smith@test.com")
                .param("name", "tim")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Student added successfully"));
    }

    @Test
    public void testAddStudentWithExistingEmail() throws Exception {
        when(studentRepository.findByEmail(anyString())).thenReturn(new Student());

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                .param("email", "tim.smith@test.com")
                .param("name", "t")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("A student with this email already exists"));
    }

    @Test
    public void testPutStudentOnHold() throws Exception {
        when(studentRepository.findById(1)).thenReturn(Optional.of(new Student()));

        mockMvc.perform(MockMvcRequestBuilders.put("/student/1/hold")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Student registration put on hold"));
    }

    @Test
    public void testPutStudentOnHoldWithNonExistingStudent() throws Exception {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/student/1/hold")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Student not found"));
    }

    @Test
    public void testReleaseStudentHold() throws Exception {
        when(studentRepository.findById(1)).thenReturn(Optional.of(new Student()));

        mockMvc.perform(MockMvcRequestBuilders.put("/student/1/release")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hold on student registration released"));
    }

    @Test
    public void testReleaseStudentHoldWithNonExistingStudent() throws Exception {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.put("/student/1/release")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("Student not found"));
    }
}
