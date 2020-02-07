//package com.example.testjenkins;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.gds.xelerator.consolebackend.BaseProjectUnitTest;
//import com.gds.xelerator.consolebackend.domain.RunsStateMachine;
//import com.gds.xelerator.consolebackend.domain.request.ProjectVariablesRequest;
//import com.gds.xelerator.consolebackend.domain.request.StoreBlueprintRequest;
//import com.gds.xelerator.consolebackend.domain.response.ProjectVariablesResponse;
//import com.gds.xelerator.consolebackend.entity.enums.TbProjectType;
//import com.gds.xelerator.consolebackend.entity.tables.pojos.TbBlueprint;
//import com.gds.xelerator.consolebackend.entity.tables.pojos.TbProject;
//import com.gds.xelerator.consolebackend.entity.tables.pojos.TbRepository;
//import com.gds.xelerator.consolebackend.entity.tables.pojos.TbRuns;
//import com.gds.xelerator.consolebackend.service.BlueprintService;
//import com.gds.xelerator.consolebackend.service.ProjectVariableService;
//import com.gds.xelerator.consolebackend.service.RunService;
//import com.gds.xelerator.consolebackend.service.VcsRepositoryService;
//import org.jooq.DSLContext;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.greaterThanOrEqualTo;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//public class ProjectControllerTest extends BaseProjectUnitTest {
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private RunService runService;
//    @Autowired
//    private ProjectVariableService projectVariableService;
//    @Autowired
//    private BlueprintService blueprintService;
//    @Autowired
//    private VcsRepositoryService repositoryService;
//
//    @Test
//    @DisplayName("检查repository")
//    public void checkRepository() throws Exception {
//        ObjectNode params = objectMapper.createObjectNode();
//        params.put("username", "chenrui")
//                .put("password", "chenrui0810")
//                .put("vcsRepo", "http://10.10.32.4/xelerator/sdc/console.git");
//        this.mockMvc.perform(post("/api/project/checkRepository")
//                .content(params.toString())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        Mockito.when(super.getJGitActionService().checkRepository(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(false);
//        params.put("username", "chenrui123");
//        this.mockMvc.perform(post("/api/project/checkRepository")
//                .content(params.toString())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is5xxServerError())
//                .andExpect(content().string("验证仓库不通过"));
//        params.remove("vcsRepo");
//        this.mockMvc.perform(post("/api/project/checkRepository")
//                .content(params.toString())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().is4xxClientError());
//    }
//
//    @Test
//    @DisplayName("新建code项目")
//    public void shouldCreateCodeProject() throws Exception {
//        String json = objectMapper.writeValueAsString(mockCodeProject());
//        ObjectNode resJson = objectMapper.createObjectNode();
//        try {
//            String response = this.mockMvc.perform(put(apiUrlPrefix + "/code").content(json).contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andReturn().getResponse().getContentAsString();
//            resJson = objectMapper.readValue(response, ObjectNode.class);
//            assertEquals(TbProjectType.code.name(), resJson.get("type").asText());
//            assertNotNull(resJson.get("projectId"));
//        } finally {
//            projectService.deleteDetachProject(resJson.get("projectId").asText());
//        }
//    }
//
//    @Test
//    @DisplayName("新建blueprint项目")
//    public void shouldCreateBlueprintProject() throws Exception {
//        String json = objectMapper.writeValueAsString(mockBlueprintProject());
//        ObjectNode resJson = objectMapper.createObjectNode();
//        try {
//            String response = this.mockMvc.perform(put(apiUrlPrefix + "/blueprint").content(json).contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andReturn().getResponse().getContentAsString();
//            resJson = objectMapper.readValue(response, ObjectNode.class);
//            String projectId = resJson.get("projectId").asText();
//            assertNotNull(projectId);
//            assertEquals(TbProjectType.blueprint.name(), resJson.get("type").asText());
//            TbBlueprint blueprint = blueprintService.getByProjectId(projectId);
//            assertNotNull(blueprint);
//            assertNull(blueprint.getDocData());
//            assertNull(blueprint.getMxData());
//        } finally {
//            projectService.deleteDetachProject(resJson.get("projectId").asText());
//        }
//    }
//
//    @Test
//    @DisplayName("更新code项目")
//    public void shouldUpdateCodeProject() throws Exception {
//        TbProject project = saveCodeProject();
//        try {
//            ObjectNode params = objectMapper.createObjectNode();
//            params.put("name", "update_project")
//                    .put("username", "zhangsan")
//                    .put("password", "buzhidao")
//                    .put("branch", "0.8")
//                    .put("accessType", "private");
//            String url = String.format("%s/code/%s", apiUrlPrefix, project.getProjectId());
//            mockMvc.perform(put(url).content(params.toString()).contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//            TbProject newProject = projectService.getProject(project.getId());
//            assertEquals("update_project", newProject.getName());
//            assertEquals("private", newProject.getAccessType().getLiteral());
//            TbRepository repository = repositoryService.getByProjectId(project.getProjectId());
//            assertNotNull(repository);
//            assertEquals("zhangsan", repository.getUsername());
//            assertEquals("buzhidao", repository.getPassword());
//
//            TbBlueprint blueprint = blueprintService.getByProjectId(project.getId());
//            Assertions.assertNull(blueprint);
//        } finally {
//            projectService.deleteDetachProject(project.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("更新blueprint项目")
//    public void shouldUpdateBlueprintProject() throws Exception {
//        TbProject project = saveBlueprintProject();
//        try {
//            ObjectNode params = objectMapper.createObjectNode();
//            params.put("name", "update_project")
//                    .put("accessType", "private");
//            String url = String.format("%s/blueprint/%s", apiUrlPrefix, project.getProjectId());
//            mockMvc.perform(put(url).content(params.toString()).contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//            TbProject newProject = projectService.getProject(project.getId());
//            Assertions.assertEquals("update_project", newProject.getName());
//            Assertions.assertEquals("private", newProject.getAccessType().getLiteral());
//            TbRepository repository = repositoryService.getByProjectId(project.getProjectId());
//            Assertions.assertNull(repository);
//            TbBlueprint blueprint = blueprintService.getByProjectId(project.getId());
//            Assertions.assertNotNull(blueprint);
//            Assertions.assertEquals(project.getId(), blueprint.getProjectId());
//        } finally {
//            projectService.deleteDetachProject(project.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("保存blueprint")
//    public void shouldDidSaveBlueprint() throws Exception {
//        TbProject project = saveBlueprintProject();
//        try {
//            String json = readFile("blueprint/test.json");
//            String url = String.format("%s/blueprint/%s", apiUrlPrefix, project.getProjectId());
//            mockMvc.perform(post(url).content(json).contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//            TbBlueprint blueprint = blueprintService.getByProjectId(project.getProjectId());
//            assertNotNull(blueprint.getDocData());
//            assertNotNull(blueprint.getMxData());
//        } finally {
//            projectService.deleteDetachProject(project.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("查询blueprint")
//    public void shouldQueryBlueprint() throws Exception {
//        TbProject project = saveBlueprintProject();
//        try {
//            String json = readFile("blueprint/test.json");
//
//            JsonNode data =objectMapper.readTree(json);
//            StoreBlueprintRequest req = new StoreBlueprintRequest();
//            req.setMxData(data.get("mxData").asText());
//            req.setDocData(data.get("docData"));
//            blueprintService.update(project.getProjectId(), req);
//            String url = String.format("%s/blueprint/%s", apiUrlPrefix, project.getProjectId());
//            String content = mockMvc.perform(get(url))
//                    .andExpect(status().isOk())
//                    .andReturn().getResponse().getContentAsString();
//            assertNotNull(content);
//            JsonNode node = objectMapper.readTree(content);
//            assertTrue(node.has("mxData"));
//            assertTrue(node.has("docData"));
//        } finally {
//            projectService.deleteDetachProject(project.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("查询某个用户项目")
//    public void shouldQueryProject() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            mockMvc.perform(get(apiUrlPrefix).contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.length()", greaterThanOrEqualTo(1)));
//            mockMvc.perform(get(apiUrlPrefix)
//                    .param("name", "test_project")
//                    .param("type", "self")
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.length()", equalTo(1)));
//            mockMvc.perform(get(apiUrlPrefix)
//                    .param("type", "public")
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.length()", equalTo(0)));
//
//            Mockito.when(idProviderApiService.getUser()).thenReturn(getAdmin());
//
//            mockMvc.perform(get(apiUrlPrefix)
//                    .param("name", "test_project")
//                    .param("type", "self")
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.length()", equalTo(0)));
//            mockMvc.perform(get(apiUrlPrefix)
//                    .param("name", "test_project")
//                    .param("type", "public")
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.length()", equalTo(1)));
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("查询指定项目")
//    public void shouldQueryProjectById() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            String reqUrl = String.format("%s/%s", apiUrlPrefix, saved.getProjectId());
//            mockMvc.perform(get(reqUrl).contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.projectId", equalTo(saved.getProjectId())));
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    private JsonNode planProject(String projectId) throws Exception {
//        String reqUrl = String.format("%s/%s/runs/plan", apiUrlPrefix, projectId);
//        ObjectNode params = objectMapper.createObjectNode();
//        params.put("reason", "first plan");
//        String response = mockMvc.perform(post(reqUrl)
//                .content(params.toString())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//        return objectMapper.readValue(response, ObjectNode.class);
//    }
//
//    @Test
//    @DisplayName("对项目执行Plan动作")
//    public void shouldPlanProject() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            JsonNode resJson = this.planProject(saved.getProjectId());
//            assertNotNull(resJson.get("runsId"));
//            Assertions.assertEquals(RunsStateMachine.PENDING.getValue().intValue(), resJson.get("status").asInt());
//            Thread.sleep(1000);
//            TbRuns runs = runService.getRunsByRunsId(resJson.get("runsId").asText());
//            Assertions.assertEquals(RunsStateMachine.PLAN.getValue(), runs.getStatus());
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    @Autowired
//    private DSLContext dsl;
//
//    @Test
//    @DisplayName("对项目执行Apply动作")
//    public void shouldApplyProject() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            JsonNode planJson = this.planProject(saved.getProjectId());
//            String runsId = planJson.get("runsId").asText();
//            runService.updateRuns(runsId, RunsStateMachine.APPROVAL);
//            String postUrl = String.format("%s/runs/%s/apply", apiUrlPrefix, planJson.get("runsId").asText());
//            mockMvc.perform(post(postUrl)
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//            TbRuns runs = runService.getRunsByRunsId(runsId);
//            Assertions.assertEquals(RunsStateMachine.APPLY.getValue(), runs.getStatus());
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("配置变量")
//    public void shouldConfigVariables() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            ObjectNode variable = objectMapper.createObjectNode();
//            variable.put("key", "region")
//                    .put("value", "qingdao")
//                    .put("hcl", false)
//                    .put("sensitive", false);
//            ArrayNode variableList = objectMapper.createArrayNode().add(variable);
//            String reqUrl = String.format("%s/%s/variables", apiUrlPrefix, saved.getProjectId());
//            String content = mockMvc.perform(put(reqUrl)
//                    .content(variableList.toString())
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.length()", equalTo(1)))
//                    .andExpect(jsonPath("$[0].key", equalTo("region")))
//                    .andReturn().getResponse().getContentAsString();
//            ArrayNode node = (ArrayNode) objectMapper.readTree(content);
//            ObjectNode var = (ObjectNode) node.get(0);
//            ObjectNode copyVariable = variable.deepCopy();
//            copyVariable.put("id",var.get("id").asText());
//            copyVariable.put("value","shanghai");
//            variableList.add(copyVariable);
//            mockMvc.perform(put(reqUrl)
//                    .content(variableList.toString())
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andExpect(jsonPath("$.length()", equalTo(2)))
//                    .andExpect(jsonPath("$[0].value", equalTo("shanghai")))
//                    .andReturn().getResponse().getContentAsString();
//
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("查询指定project变量")
//    public void shouldQueryVariables() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            ProjectVariablesRequest variables = new ProjectVariablesRequest();
//            variables.setKey("region");
//            variables.setValue("qingdao");
//            variables.setSensitive(false);
//            variables.setHcl(false);
//            projectVariableService.save(saved.getProjectId(), Arrays.asList(variables));
//            String reqUrl = String.format("%s/%s/variables", apiUrlPrefix, saved.getProjectId());
//            String content = mockMvc.perform(get(reqUrl)
//                    .content(variables.toString())
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andReturn().getResponse().getContentAsString();
//            ArrayNode result = objectMapper.readValue(content, ArrayNode.class);
//            Assertions.assertTrue(result.size() > 0);
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("删除project变量")
//    public void shouldDeleteVariables() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            ProjectVariablesRequest variables = new ProjectVariablesRequest();
//            variables.setKey("region");
//            variables.setValue("qingdao");
//            variables.setSensitive(false);
//            variables.setHcl(false);
//            List<ProjectVariablesResponse> variablesResponses = projectVariableService.save(saved.getProjectId(), Arrays.asList(variables));
//            String reqUrl = String.format("%s/variables/%s", apiUrlPrefix, variablesResponses.get(0).getId());
//            String content = mockMvc.perform(delete(reqUrl)
//                    .content(variables.toString())
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andReturn().getResponse().getContentAsString();
//            Assertions.assertTrue(Integer.parseInt(content) == 1);
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("放弃执行")
//    public void shouldDiscard() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            JsonNode planJson = this.planProject(saved.getProjectId());
//            String runsId = planJson.get("runsId").asText();
//            runService.updateRuns(runsId, RunsStateMachine.APPROVAL);
//            String reqUrl = String.format("%s/runs/%s/discard", apiUrlPrefix, runsId);
//            mockMvc.perform(post(reqUrl)
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//            TbRuns runs = runService.getRunsByRunsId(runsId);
//            Assertions.assertEquals(RunsStateMachine.DISCARDED.getValue(), runs.getStatus());
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//
//    }
//
//    @Test
//    @DisplayName("删除项目")
//    public void shouldDeleteProject() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            String reqUrl = String.format("%s/%s", apiUrlPrefix, saved.getProjectId());
//            mockMvc.perform(delete(reqUrl)
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk());
//            saved = saveCodeProject();
//            JsonNode planProject = this.planProject(saved.getProjectId());
//            runService.updateRuns(planProject.get("runsId").asText(), RunsStateMachine.PLANNING);
//            reqUrl = String.format("%s/%s", apiUrlPrefix, saved.getProjectId());
//            mockMvc.perform(delete(reqUrl)
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().is5xxServerError())
//                    .andExpect(content().string("当前有任务在执行，请稍后再试..."));
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//
//    @Test
//    @DisplayName("destroy project")
//    public void shouldDestroyProject() throws Exception {
//        TbProject saved = saveCodeProject();
//        try {
//            String reqUrl = String.format("%s/%s/runs/destroy", apiUrlPrefix, saved.getProjectId());
//            String content = mockMvc.perform(post(reqUrl)
//                    .contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isOk())
//                    .andReturn().getResponse().getContentAsString();
//            ObjectNode result = objectMapper.readValue(content, ObjectNode.class);
//            Assertions.assertTrue(result.get("destroy").asBoolean());
//            Assertions.assertEquals(RunsStateMachine.PENDING.getValue().intValue(), result.get("status").asInt());
//        } finally {
//            projectService.deleteDetachProject(saved.getProjectId());
//        }
//    }
//}
