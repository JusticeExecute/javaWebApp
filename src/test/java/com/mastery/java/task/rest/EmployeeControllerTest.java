package com.mastery.java.task.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.App;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EmployeeControllerTest.class)
@Import(App.class)
@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    private static ObjectMapper mapper = new ObjectMapper();
    @InjectMocks
    EmployeeController employeeController;
    @Mock
    EmployeeService employeeService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();

    }

    @Test
    void testAddEmployee() throws Exception {
        Employee employee = new Employee(99L, "Alex", "Blackovich", Gender.MALE, 22);
        Mockito.when(employeeService.newEmployee(ArgumentMatchers.any())).thenReturn(employee);
        String json = mapper.writeValueAsString(employee);
        this.mockMvc.perform(post("http://localhost:8080/api/v1/employees").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId", Matchers.equalTo(99)))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Alex")))
                .andExpect(jsonPath("$.gender", Matchers.equalTo("MALE")));
    }
}

