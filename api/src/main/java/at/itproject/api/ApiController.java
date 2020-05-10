package at.itproject.api;

import at.itproject.core.ApiServiceImpl;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
public class ApiController {

    String ip;
    String influxURL;
    InfluxDB influxDB;
    String databaseName;
    String printjobUUID;
    int timeoutCounter;
    int maxTimeout;

    @Autowired
    Environment environment;

    @PostConstruct
    public void init() {
        ip = "192.168.0.1";
        influxURL = environment.getProperty("influx.url");
        databaseName = environment.getProperty("influx.name");
        printjobUUID = "";
        timeoutCounter = 20;

        // init database
        influxDB = InfluxDBFactory.connect(influxURL);
        influxDB.query(new Query("CREATE DATABASE " + databaseName));
        influxDB.setDatabase(databaseName);
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
    }

    //@Scheduled(fixedRate = 10000)
    public void printerStatus() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.writePrinterStatus(ip, influxDB);
        // Close it if your application is terminating or you are not using it anymore.
        //influxDB.close();
    }

    //@Scheduled(fixedRate = 10000)
    public void hotendTemperatures() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.writeHotendTemperatures(ip, influxDB, databaseName);
    }

    //@Scheduled(fixedRate = 10000)
    public void timeSpentHot() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.writeTimeSpentHot(ip, influxDB, databaseName);
    }

    @Scheduled(fixedRate = 10000)
    public void materialExtruded() {
        ApiServiceImpl apiService = new ApiServiceImpl();
        apiService.writeMaterialExtruded(ip, influxDB, databaseName);
    }


    //@Scheduled(fixedRate = 10000)
    public void printjobHistory() {
        if(printjobUUID.compareTo("")!=0){
            ApiServiceImpl apiService=new ApiServiceImpl();
            timeoutCounter = apiService.writePrintJobHistory(ip,influxDB,printjobUUID,timeoutCounter,maxTimeout);
        }
    }

    //@Scheduled(fixedRate = 10000)
    public void printjobProgress() {
        ApiServiceImpl apiService=new ApiServiceImpl();
        String uuid = apiService.writePrintJobProgress(ip,influxDB);
        if(printjobUUID.compareTo("")==0)
            printjobUUID = uuid;
    }
}
