package at.itproject.core;

import at.itproject.api.ApiController;
import io.swagger.client.ApiException;
import io.swagger.client.api.HistoryApi;
import io.swagger.client.api.PrintJobApi;
import io.swagger.client.api.PrinterApi;
import io.swagger.client.model.Head;
import io.swagger.client.model.PrintJobHistory;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class ApiServiceImpl {

    Logger logger = Logger.getLogger(ApiServiceImpl.class.getName());

    private Calendar calendar=Calendar.getInstance();
    String[] strDays=new String[]{"Sunday","Monday","Tuesday",
            "Wednesday","Thursday","Friday","Saturday"};
    String[] strMonths=new String[]{"January","February","March",
            "April","May","June","July","August","September","October","November","December"};
    //@Value("${spring.data.rest.base-path}")
    private String basepath="http://";
    RestTemplate restTemplate = new RestTemplate();

    private int i=1;

    public static boolean pingHost(String host,int timeout) {
        try {
            InetAddress address=InetAddress.getByName(host);
            boolean reachable=address.isReachable(timeout);
            return reachable;
        } catch (Exception e) {
            return false;
        }
    }

    public void writePrinterStatus(String ip,InfluxDB influxDB) {

        String id=ip.split("\\.")[3];
        PrinterApi printerApi=new PrinterApi();
        printerApi.getApiClient().setBasePath(basepath+ip+"/api/v1");
        String status;


        if(pingHost(ip,500)){
            try {
                status=printerApi.printerStatusGet();
            } catch (ApiException e) {
                status="unknown";
            }
        }else{
            status="unknown";
        }

        // Write points to InfluxDB.
        influxDB.write(Point.measurement("printer_status")
                .time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                .tag("printer",id)
                .tag("weekday",strDays[calendar.get(Calendar.DAY_OF_WEEK)-1])
                .tag("month",strMonths[calendar.get(Calendar.MONTH)])
                .tag("year",String.valueOf(calendar.get(Calendar.YEAR)))
                .addField("status",status)
                .build());

    }

    public boolean writeHotendTemperatures(String ip,InfluxDB influxDB,String databaseName) {
        String id=ip.split("\\.")[3];
        PrinterApi printerApi=new PrinterApi();
        printerApi.getApiClient().setBasePath(basepath+ip+"/api/v1");
        //List<LineChartDto> measurements = new ArrayList<>();
        List<Head> heads;
        BatchPoints batchPoints=BatchPoints
                .database(databaseName)
                //.retentionPolicy("defaultPolicy")
                .build();

        if(pingHost(ip,500)){
            heads=getHead(printerApi);
            try {
                if(heads != null){
                    heads.forEach(head->head.getExtruders().forEach(extruder->{

                                Point point=Point.measurement("temperature_hotend")
                                        .time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                                        .tag("printer",id)
                                        .tag("extruder",String.valueOf(i++))
                                        .tag("nozzleType",extruder.getHotend().getId().replace(" ","_"))
                                        .addField("temperature",extruder.getHotend().getTemperature().getCurrent().toString())
                                        .build();
                                batchPoints.point(point);
                            })
                    );
                    influxDB.write(batchPoints);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            return false;
        }

        return true;

    }

    public void writeTimeSpentHot(String ip,InfluxDB influxDB,String databaseName) {
        PrinterApi printerApi=new PrinterApi();
        String id=ip.split("\\.")[3];
        printerApi.getApiClient().setBasePath(basepath+ip+"/api/v1");
        List<Head> heads;
        //List<LineChartDto> measurements = new ArrayList<>();
        BatchPoints batchPoints=BatchPoints
                .database(databaseName)
                //.retentionPolicy("defaultPolicy")
                .build();

        if(pingHost(ip,500)){
            heads=getHead(printerApi);
            try {
                if(heads != null){
                    heads.forEach(head->head.getExtruders().forEach(extruder->{

                                Point point=Point.measurement("time_spent_hot")
                                        .time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                                        .tag("printer",id)
                                        .tag("extruder",String.valueOf(i++))
                                        .tag("nozzleType",extruder.getHotend().getId().replace(" ","_"))
                                        .tag("weekday",strDays[calendar.get(Calendar.DAY_OF_WEEK)-1])
                                        .tag("month",strMonths[calendar.get(Calendar.MONTH)])
                                        .tag("year",String.valueOf(calendar.get(Calendar.YEAR)))
                                        .addField("time-spent-hot",extruder.getHotend().getStatistics().getTimeSpentHot())
                                        .build();
                                batchPoints.point(point);
                            })
                    );
                    influxDB.write(batchPoints);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void writeMaterialExtruded(String ip,InfluxDB influxDB,String databaseName) {
        PrinterApi printerApi=new PrinterApi();
        String id=ip.split("\\.")[3];
        printerApi.getApiClient().setBasePath(basepath+ip+"/api/v1");
        List<Head> heads;
        //List<LineChartDto> measurements = new ArrayList<>();
        BatchPoints batchPoints=BatchPoints
                .database(databaseName)
                //.retentionPolicy("defaultPolicy")
                .build();

        if(pingHost(ip,500)){
            heads=getHead(printerApi);
            try {
                if(heads != null){
                    heads.forEach(head->head.getExtruders().forEach(extruder->{

                                Point point=Point.measurement("material_extruded")
                                        .time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                                        .tag("printer",id)
                                        .tag("extruder",String.valueOf(i++))
                                        .tag("nozzleType",extruder.getHotend().getId().replace(" ","_"))
                                        .tag("weekday",strDays[calendar.get(Calendar.DAY_OF_WEEK)-1])
                                        .tag("month",strMonths[calendar.get(Calendar.MONTH)])
                                        .tag("year",String.valueOf(calendar.get(Calendar.YEAR)))
                                        .addField("material-extruded",extruder.getHotend().getStatistics().getMaterialExtruded())
                                        .build();
                                batchPoints.point(point);
                            })
                    );
                    influxDB.write(batchPoints);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void writePrintJobHistory(String ip,InfluxDB influxDB,String printjobUUID, String containerID, String userID) {

        String id=ip.split("\\.")[3];
        HistoryApi historyApi=new HistoryApi();
        historyApi.getApiClient().setBasePath(basepath+ip+"/api/v1");

        if(pingHost(ip,500)){
            try {
                PrintJobHistory history=historyApi.historyPrintJobsGet(BigDecimal.valueOf(0),BigDecimal.valueOf(1)).iterator().next();
                Message message = new Message();
                String uuid=history.getUuid();
                String result=history.getResult().toString();
                if(uuid.compareTo(printjobUUID) == 0){
                    // Write points to InfluxDB
                    influxDB.write(Point.measurement("printjob_history")
                            .time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                            .tag("result",result)
                            .tag("printer",id)
                            .tag("weekday",strDays[calendar.get(Calendar.DAY_OF_WEEK)-1])
                            .tag("month",strMonths[calendar.get(Calendar.MONTH)])
                            .tag("year",String.valueOf(calendar.get(Calendar.YEAR)))
                            .addField("uuid",uuid)
                            .build());

                    // send history
                    message.setReferenceId(userID);
                    message.setPrintJobHistory(history);
                    restTemplate.postForLocation("https://connector.grandgarage.eu/api/add-metadata", message);

                    logger.info("send history and kill container");
                    // stop container
                    //System.out.println("shutdown finish");
                    restTemplate.postForLocation("http://10.0.0.42:8081/api/v1/stop", containerID);
                    //restTemplate.postForLocation("http://10.5.1.155:8081/api/v1/stop", containerID);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String writePrintJobProgress(String ip,InfluxDB influxDB) {
        String id=ip.split("\\.")[3];
        PrintJobApi printJobApi=new PrintJobApi();
        PrinterApi printerApi=new PrinterApi();
        printJobApi.getApiClient().setBasePath(basepath+ip+"/api/v1");
        printerApi.getApiClient().setBasePath(basepath+ip+"/api/v1");
        long progress;
        String name;

        if(pingHost(ip,500)){
            try {
                if(printerApi.printerStatusGet().compareTo("printing") == 0){
                    progress=Math.round(printJobApi.printJobProgressGet().doubleValue() * 100);
                    name=printJobApi.printJobNameGet().replace(" ","_");
                    if(name.compareTo("") != 0){
                        influxDB.write(Point.measurement("printjob_progress")
                                .time(System.currentTimeMillis(),TimeUnit.MILLISECONDS)
                                .tag("printer",id)
                                .tag("jobname",name)
                                .addField("progress",progress)
                                .build());

                        return printJobApi.printJobUuidGet();

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private List<Head> getHead(PrinterApi printerApi) {

        try {
            return printerApi.printerHeadsGet();
        } catch (ApiException e) {
            logger.warning("get head not possible");
            return null;
        }
    }
}
