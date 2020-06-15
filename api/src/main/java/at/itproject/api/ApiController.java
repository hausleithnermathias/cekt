package at.itproject.api;

import at.itproject.core.ApiServiceImpl;
import at.itproject.core.PrintJobTimout;
import io.swagger.client.ApiException;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Controller
public class ApiController {

    String ip;
    String influxURL;
    String schedulerIp;
    String managementToolApi;
    InfluxDB influxDB;
    String databaseName;
    String printjobUUID;
    String containerID;
    String referenceID;
    int timeoutCounter;
    int maxTimeout;

    @Autowired
    Environment environment;

    @PostConstruct
    public void init() {

        ip = System.getenv("printerIP");
        containerID = System.getenv("containerID");
        referenceID = System.getenv("userID");
        schedulerIp = System.getenv("schedulerIP");

        //System.out.println("scheduler:" + schedulerIp);
        //System.out.println("container:" + containerID);
        //System.out.println("reference:" + referenceID);

        influxURL = "http://influxdb:8086";
        databaseName = "ultimaker";
        managementToolApi = "connector.grandgarage.eu/api/add-metadata";
        printjobUUID = "";
        timeoutCounter = 0;
        maxTimeout = 5;

        // init databases
        influxDB = InfluxDBFactory.connect(influxURL);
        influxDB.query(new Query("CREATE DATABASE " + databaseName));
        influxDB.setDatabase(databaseName);
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Scheduled(fixedRate = 20000)
    public void printerStatus() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.writePrinterStatus(ip, influxDB);
    }

    @Scheduled(fixedRate = 60000)
    public void hotendTemperatures() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        if(apiService.writeHotendTemperatures(ip, influxDB, databaseName)) {
            timeoutCounter = 0;
        }
        else {
            timeoutCounter++;
            if(timeoutCounter >= maxTimeout) {
                RestTemplate restTemplate = new RestTemplate();
                PrintJobTimout printJobTimout = new PrintJobTimout();
                printJobTimout.setReferenceId(referenceID);

                // send timeout message
                restTemplate.postForLocation("https://connector.grandgarage.eu/api/add-metadata", printJobTimout);
            }
        }

    }

    @Scheduled(fixedRate = 70000)
    public void timeSpentHot() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.writeTimeSpentHot(ip, influxDB, databaseName);
    }

    @Scheduled(fixedRate = 80000)
    public void materialExtruded() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.writeMaterialExtruded(ip, influxDB, databaseName);
    }

    @Scheduled(fixedRate = 90000)
    public void printjobHistory() {
        if(printjobUUID.compareTo("")!=0){
            ApiServiceImpl apiService=new ApiServiceImpl();
            apiService.writePrintJobHistory(ip,influxDB,printjobUUID, containerID, referenceID);
        }
    }

    @Scheduled(fixedRate = 30000)
    public void printjobProgress() {
        ApiServiceImpl apiService=new ApiServiceImpl();
        String uuid = apiService.writePrintJobProgress(ip,influxDB);
        if(printjobUUID.compareTo("")==0 && uuid.compareTo("")!=0)
            printjobUUID = uuid;
    }


}
