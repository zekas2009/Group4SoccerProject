import AzamatsSolutions.POJO.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by nurkulov 12/26/19
 */
public class APITasks {

    /*
     * GET all soccer team names listed in given resource
     * Deserialization type: Pojo
     */

    public static List<String> getAllTeams() throws URISyntaxException, IOException {


        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/teams/");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse httpResponse = httpClient.execute(httpGet);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        TeamsPojo teamsPojo = objectMapper.readValue(httpResponse.getEntity().getContent(), TeamsPojo.class);


        List<String> teams = new ArrayList<>();


        for (int i = 0; i < teamsPojo.getTeams().size(); i++) {
            teams.add(teamsPojo.getTeams().get(i).getName());

        }


        return teams;
    }

    /*
     * GET names of all goalkeepers from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getAllGoalkeepers() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
//http://api.football-data.org/v2/teams/
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/teams/66");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse httpResponse = httpClient.execute(httpGet);

        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        Map<String, Object> mapper = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> squad = (List<Map<String, Object>>) mapper.get("squad");
        List<String> goalKeepers = new ArrayList<>();


        for (int i = 0; i < squad.size(); i++) {


            String position = "" + squad.get(i).get("position");
            String name = "" + squad.get(i).get("name");


            if (position.equalsIgnoreCase("Goalkeeper")) {
                goalKeepers.add(name);
            }

        }

        return goalKeepers;


    }

    /*
     * GET names of all defenders from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getDefenders() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
//http://api.football-data.org/v2/teams/
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/teams/66");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse httpResponse = httpClient.execute(httpGet);

        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        Map<String, Object> mapper = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>() {
                });

        List<Map<String, Object>> squad = (List<Map<String, Object>>) mapper.get("squad");
        List<String> defenders = new ArrayList<>();


        for (int i = 0; i < squad.size(); i++) {


            String position = "" + squad.get(i).get("position");
            String name = "" + squad.get(i).get("name");


            if (position.equalsIgnoreCase("Defender")) {
                defenders.add(name);
            }

        }

        return defenders;

    }

    /*
     * GET names of all midfielders from England team
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielders() throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/teams/66");
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        List<String> midfilders = new ArrayList<>();

        SquadsPojo desrializeMidfielders = objectMapper.readValue(response.getEntity().getContent(), SquadsPojo.class);


        for (int i = 0; i < desrializeMidfielders.getSquad().size(); i++) {

            String position = "" + desrializeMidfielders.getSquad().get(i).get("position");
            String name = "" + desrializeMidfielders.getSquad().get(i).get("name");

            if (position.equalsIgnoreCase("Midfielder")) {
                midfilders.add(name);
            }


        }

        return midfilders;
    }

    /*
     * GET names of all midfielders from England team whose country is Brazil
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielderFromBrazil() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/teams/66");
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        List<String> midfildersFromBrazil = new ArrayList<>();

        SquadsPojo desrializeMidfielders = objectMapper.readValue(response.getEntity().getContent(), SquadsPojo.class);


        for (int i = 0; i < desrializeMidfielders.getSquad().size(); i++) {

            String position = "" + desrializeMidfielders.getSquad().get(i).get("position");
            String countryOfBirth = "" + desrializeMidfielders.getSquad().get(i).get("nationality");
            ;
            String name = "" + desrializeMidfielders.getSquad().get(i).get("name");

            if (position.equalsIgnoreCase("Midfielder") && countryOfBirth.equalsIgnoreCase("Brazil")) {
                midfildersFromBrazil.add(name);
            }


        }

        return midfildersFromBrazil;

    }

    /*
     * GET names of all attackers from England team whose origin country is England
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getAttackerFromEngland() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/teams/66");
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse response = client.execute(get);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        List<String> attackersFromEngland = new ArrayList<>();

        SquadsPojo desrializeMidfielders = objectMapper.readValue(response.getEntity().getContent(), SquadsPojo.class);


        for (int i = 0; i < desrializeMidfielders.getSquad().size(); i++) {

            String position = "" + desrializeMidfielders.getSquad().get(i).get("position");
            String countryOfBirth = "" + desrializeMidfielders.getSquad().get(i).get("countryOfBirth");
            ;
            String name = "" + desrializeMidfielders.getSquad().get(i).get("name");

            if (position.equalsIgnoreCase("Attacker") && countryOfBirth.equalsIgnoreCase("England")) {
                attackersFromEngland.add(name);
            }


        }

        return attackersFromEngland;

    }

    /*
     * GET name of Spain team coach
     * note: Spain team id is 77
     * Deserialization type: Pojo
     */
    public static List<String> getSpainCoach() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/teams/77");
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse httpResponse = client.execute(get);
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();


        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SpainTeamPojo spainTeamPojo = objectMapper.readValue(httpResponse.getEntity().getContent(), SpainTeamPojo.class);

        List<String> coachessFromSpain = new ArrayList<>();

        for (int i = 0; i < spainTeamPojo.getSquad().size(); i++) {

            String name = "" + spainTeamPojo.getSquad().get(i).get("name");
            String role = "" + spainTeamPojo.getSquad().get(i).get("role");
            if (role.equalsIgnoreCase("COACH")) {
                coachessFromSpain.add(name);

            }

        }

        return coachessFromSpain;

    }

    /*
    GET list of all competitions
    Deserialization type: POJO
     */
    public static List<String> getAllCompetitions() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/competitions");
        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse httpResponse = client.execute(get);
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();


        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        CompetitionsPojo spainTeamPojo = objectMapper.readValue(httpResponse.getEntity().getContent(), CompetitionsPojo.class);


        List<String> competitionsList = new ArrayList<>();


        for (int i = 0; i < spainTeamPojo.getCompetitions().size(); i++) {

            String competition = "" + spainTeamPojo.getCompetitions().get(i).get("name");
            competitionsList.add(competition);
            System.out.print(" [" + competition + "] ");

        }

        System.out.println("" +
                "");


        return competitionsList;

    }

    /*
     * GET names of second highest scorrer from competitions of 2000 season
     * note: endpoint for competitions: `competitions/<year>/
     * note: endpoint for scorers: `competitions/<year>/scorers`
     * Deserialization type: Pojo and TypeReference
     */


    public static List<String> getSecondHighestScorer() throws URISyntaxException, IOException {

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http");
        uriBuilder.setHost("api.football-data.org");
        uriBuilder.setPath("v2/competitions/2000/scorers");

        HttpGet get = new HttpGet(uriBuilder.build());
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("X-Auth-Token", "23313095d88c47c8a01362bf1adc1e6d");

        HttpResponse httpResponse = client.execute(get);
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();


        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Map<String, Object> scorersPojo = objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Map<String, Object>>() {
        });

        List<Map<String, Object>> scorersTable = (List<Map<String, Object>>) scorersPojo.get("scorers");

        Map<String, Object> player = new HashMap<>();

        List<String> scorersNames = new ArrayList<>();

        TreeSet<String> tressSet = new TreeSet<>();


        for (int i=0; i < scorersTable.size(); i++) {



            String numberOfScores = ""+scorersTable.get(i).get("numberOfGoals");

            tressSet.add(numberOfScores);

        }

        List<String> list = new ArrayList<>();
        list.addAll(tressSet);


        for(int i=0; i<scorersTable.size(); i++){
            player = (Map<String, Object>) scorersTable.get(i).get("player");
            String numberOfScores = ""+scorersTable.get(i).get("numberOfGoals");
            String playerName = ""+player.get("name");

            if(list.get(list.size()-2).equals(numberOfScores)){
                scorersNames.add(playerName);
            }
        }

return  scorersNames;
    }
}