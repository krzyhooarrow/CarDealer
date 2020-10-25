package spring.repository_layer.db_init.random_data;

public enum Description {

    desc1("Black 1999 Ford Mustang Cobra RWD 5-Speed Manual 4.6L V8 DOHC 32V 1 OWNER CLEAN CARFAX, * LEATHER *, Mustang Cobra, 2D Coupe, 4.6L V8 DOHC 32V, 5-Speed Manual, Black.Must See this Vehicle...$18000 worth of Upgrades and Performance options...Over 700 Horsepower!!!This vehicle is located at Jeff D'Ambrosio Suzuki in Frazer. Please call 610-251-0900 to schedule a test drive today."),
    desc2("One Owner! FUEL EFFICIENT 22 MPG Hwy/16 MPG City! Heated Seats, Alloy Wheels, Tow Hitch, 4x4, Onboard Communications System, CD Player, SEATING, HEATED DRIVER AND FRONT PASS CHEVROLET MYLINK AUDIO SYSTEM, 8' DIA ALL STAR EDITION READ MORE! KEY FEATURES INCLUDE 4x4, CD Player, Onboard Communications System, Trailer Hitch, Chrome Wheels. Keyless Entry, Privacy Glass, Steering Wheel Controls, Heated Mirrors, Electronic Stability Control. OPTION PACKAGES ALL STAR EDITION includes (AG1) driver 10-way power seat adjuster with (AZ3) bench seat only, (CJ2) dual-zone climate control, (BTV) Remote Vehicle Starter system, (IO5) MyLink 8' Diagonal Color Touch Screen audio system, (UVC) Rear Vision Camera, (C49) rear-window defogger and (KI4) 110-volt power outlet and 18' aluminum wheels (Includes (RD1) 18' x 8.5' bright-machined aluminum wheels. ENGINE, 5.3L FLEXFUEL ECOTEC3 V8 WITH ACTIVE FUEL MANAGEMENT, DIRECT INJECTION AND VARIABLE VALVE TIMING includes aluminum block construction with Flex Fuel capability, capable of running on unleaded or up to 85% ethanol (355 hp [265 kW] @ 5600 rpm, 383 lb-ft of torque [518 Nm] @ 4100 rpm; more than 300 lb-ft of torque from 2000 to 5600 rpm), SEATING, HEATED DRIVER AND FRONT PASSENGER, SEATS, FRONT 40/20/40 SPLIT-BENCH, 3-PASSENGER, AVAILABLE IN CLOTH OR LEATHER includes driver and front passenger recline with outboard head restraints and center fold-down armrest with storage. Also includes manually adjustable driver lumbar, lockable storage compartment in seat cushion, and storage pockets. (STD), TRANSMISSION, 6-SPEED AUTOMATIC, ELECTRONICALLY CONTROLLED with overdrive and tow/haul mode. Includes Cruise Grade Braking and Powertrain Grade Braking (STD) Pricing analysis performed on 9/3/2020. Horsepower calculations based on trim engine configuration. Fuel economy calculations based on original manufacturer data for trim engine configuration. Please confirm the accuracy of the included equipment by calling us prior to purchase."),
    desc3("During these uncertain times, Carvana is dedicated to ensuring safety for all of our customers.\n" +
            "\n" +
            "In addition to our 100% online shopping and selling experience that allows all customers to buy and trade their cars without ever leaving the safety of their home, we're providing Touchless Delivery that make all aspects of our process even safer. You can get the car you want, and trade in the one you have, while avoiding person-to-person contact with our friendly advocates. There are some things that can't be put off. If buying a car is one of them, know that we're doing everything we can to keep you keep moving while continuing to put your health, safety, and happiness first."),
    desc4("Vroom is a national online retailer that allows you to shop thousands of high-quality vehicles online, delivered straight to you contact-free. With Vroom, browse and shop through an extensive inventory of low-mileage, competitively priced cars, and trucks available for purchase, with easy online financing and new inventory added every day. No haggling. No pressure. Buy your next vehicle from Vroom, have it delivered straight to you, and never visit a dealership again"),
    desc5("Transfer of vehicle from another location to your neighborhood Enterprise Car Sales may require payment of a non-refundable transfer fee to begin the process. Contact a Sales Consultant for details. This vehicle passed a rigorous inspection by an ASE-Certified technician and is backed by a 12-Month/12,000 Mile Limited Powertrain Warranty(1). We offer a free CARFAX (R) Vehicle History Report (TM) and a 7-Day Buyback (2) to give you peace of mind that you are buying a quality used vehicle. Call us for further details or stop by today for a test drive! 1-888-227-7253. (1) Limited Powertrain Warranty begins on the vehicle purchase date & extends for 12 months or 12,000 miles, whichever comes first. Coverage runs concurrently with manufacturer warranty. Restrictions apply. Contact a Sales Consultant for details. (2) For a period of 7 days after the date of purchase or 1,000 miles beyond the odometer reading at purchase, whichever comes first, the vehicle may be returned for the exact price originally paid minus a $200 restocking fee, (as allowed by law). Restrictions apply.Price includes Delivery and Handling fee of $149. Price does not include tax, title, tags, governmental fees, any emissions testing and/or state inspection fees, and any finance charges (if applicable).Vehicles subject to prior sale. We make every effort to provide accurate information including but not limited to price, miles and vehicle options, but please verify with your local Enterprise Car Sales location before purchasing. Current mileage may vary due to test drives and vehicle relocation. Used vehicles were previously part of Enterprise short term rental, lease fleet or purchased by Enterprise from other sources including auto auctions, with previous use possibly short term rental, lease or other.");

    private final String desc;

    Description(String name) {
        this.desc = name;
    }

    @Override
    public String toString() {
        return desc;
    }
}
