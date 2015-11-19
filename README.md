# AndroidAppSimulator-Parser
Early Performance Analysis for Android Applications - Parser for simulation list

The development of mobile applications is guided
by the careful attention to Non-Functional Requirements (NFRs),
where a good user experience is the ultimate goal. Nevertheless,
NFRs evaluation in this domain is still an ad-hoc, manual and
time consuming task. The Embedded Systems Laboratory (LSE) at UFRGS 
has recently proposed a framework for the early evaluation of the 
overall performance of a mobile device with respect to the 
characteristics of a given application.
The proposed framework combines a structured performance analysis
procedure and the automatic generation of application oriented
benchmarks and workload that allow a fast analysis of target devices. 
Experimental results have shown that NFRs evaluation based on a 
application-oriented benchmark can provide the feedback the mobile 
developer needs to improve the quality of his/her application and 
find the best trade-off between cost and performance even before 
system implementation.

The current project is separated in two parts :
-An [Android application](https://github.com/vicenteluchi/TestLibrary) allowing to simulate different kind of application.

-A [desktop Java Fx application](https://github.com/vicenteluchi/AndroidAppSimulator) allowing to generate an xml file listing the operation to simulate. 

The AndroidAppSimulator-Parser repository corresponds to the parser used to write and read the list of operations to simulate and is used in the both parts.
