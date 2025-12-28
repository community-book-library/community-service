package com.project.community;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommunityAPIIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate testRestTemplate;
//
//    @Autowired
//    private CommunityRepository communityRepository;
//
//    @Autowired
//    private CommunityService communityService;
//
//    private static HttpHeaders headers;
//    private  final ObjectMapper objectMapper = new ObjectMapper();
//
//    @BeforeAll
//    public static void init(){
//        headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//    }
//
//    private String createURLWithPort(String uri){
//        return "http://localhost:" + port + uri;
//    }
//
////    @Test
////    public void testCreateCommunity() throws JsonProcessingException{
////        CommunityDTO communityDTO = new CommunityDTO("Aspen", CommunityType.CONDOS,
////                true,(short)2,"Plainsboro","NJ","08536","100");
////        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(communityDTO),headers);
////        ResponseEntity<Short> response = testRestTemplate.exchange(createURLWithPort("/community"), HttpMethod.POST,entity,Short.class);
////        short communityId = Objects.requireNonNull(response.getBody());
////        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
////        //assertEquals("Aspen",communityRepository.findById(responseId).get().getCommunityName());
////    }
//
//    @Test
//    public void testFindCommunityById() throws  JsonProcessingException{
//        HttpEntity<String> entity = new HttpEntity<>(null,headers);
//        short communityId = 2;
//        ResponseEntity<String> response = testRestTemplate.exchange(createURLWithPort("/community/2"),HttpMethod.GET,entity,String.class);
//        String communityName = "Aspen";
//        CommunityDTO communityDTO = objectMapper.readValue(response.getBody(), CommunityDTO.class);
//        assertEquals(communityDTO.getCommunityName(),communityName);
//    }
//
//    @Test
//    public void testFindCommunityByType() throws  JsonProcessingException{
//        HttpEntity<String> entity = new HttpEntity<>(null,headers);
//        ResponseEntity<List<CommunityDTO>> response = testRestTemplate.exchange(createURLWithPort("/community?communityType=TOWNHOUSES"),HttpMethod.GET,entity, new ParameterizedTypeReference<List<CommunityDTO>>(){});
//        assertEquals(response.getBody().size(),1);
//    }
//
////    @Test
////    public void testUpdateCommunity() throws  JsonProcessingException{
////        CommunityDTO communityDTO = new CommunityDTO((short) 1,"Orleans","TOWNHOUSES",true,
////                (short) 1,"Naperville","IL","60565","1001");
////        testRestTemplate.put(createURLWithPort("/community"),communityDTO);
////        ResponseEntity<CommunityDTO> updated = testRestTemplate.getForEntity(createURLWithPort("/community/1"), CommunityDTO.class);
////        assertEquals(communityDTO.getCommunityName(),updated.getBody().getCommunityName());
////    }
//
//    @Test
//    public void testDeleteCommunityById() throws JsonProcessingException{
//        testRestTemplate.delete(createURLWithPort("/community/6"));
//        ResponseEntity<CommunityDTO> response = testRestTemplate.getForEntity(createURLWithPort("/community/6"), CommunityDTO.class);
//        assertEquals(null,response.getBody());
//    }
}
