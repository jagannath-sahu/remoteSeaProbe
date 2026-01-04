package com.dxc.remoteSeaProbe.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counter")
public class CounterController {

    /*
    sudo apt install apache2-utils
    ab -n 1000 -c 50 http://localhost:8080/counter/increment
    curl http://localhost:8080/counter/value
     */

    /*
    remoteSeaProbe$ curl http://localhost:8080/counter/counter/value
0jagannath@JSAHU-NB:~/tli/remoteSeaProbe$ ab -n 1000 -c 50 http://localhost:8080/counter/increment
This is ApacheBench, Version 2.3 <$Revision: 1879490 $>
Copyright 1996 Adam Twiss, Zeus Technology Ltd, http://www.zeustech.net/
Licensed to The Apache Software Foundation, http://www.apache.org/

Benchmarking localhost (be patient)
Completed 100 requests
Completed 200 requests
Completed 300 requests
Completed 400 requests
Completed 500 requests
Completed 600 requests
Completed 700 requests
Completed 800 requests
Completed 900 requests
Completed 1000 requests
Finished 1000 requests


Server Software:
Server Hostname:        localhost
Server Port:            8080

Document Path:          /counter/increment
Document Length:        1 bytes

Concurrency Level:      50
Time taken for tests:   0.125 seconds
Complete requests:      1000
Failed requests:        991
   (Connect: 0, Receive: 0, Length: 991, Exceptions: 0)
Total transferred:      107884 bytes
HTML transferred:       2884 bytes
Requests per second:    8017.19 [#/sec] (mean)
Time per request:       6.237 [ms] (mean)
Time per request:       0.125 [ms] (mean, across all concurrent requests)
Transfer rate:          844.65 [Kbytes/sec] received

Connection Times (ms)
              min  mean[+/-sd] median   max
Connect:        0    1   0.5      1       3
Processing:     2    5   2.2      5      16
Waiting:        1    5   2.1      4      15
Total:          2    6   2.2      6      17

Percentage of the requests served within a certain time (ms)
  50%      6
  66%      6
  75%      7
  80%      7
  90%      9
  95%     11
  98%     12
  99%     13
 100%     17 (longest request)
jagannath@JSAHU-NB:~/tli/remoteSeaProbe$  curl http://localhost:8080/counter/counter/value
948jagannath@JSAHU-NB:~/tli/remoteSeaProbe$

     */

    /* solution:
    private final AtomicInteger counter = new AtomicInteger();

@GetMapping("/counter/increment")
public int increment() {
    return counter.incrementAndGet();
}

     */

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/increment")
    public int increment() {
        return counterService.increment();
    }

    @GetMapping("/value")
    public int value() {
        return counterService.getCounter();
    }
}

