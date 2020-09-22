package spring.repository_layer.models.cars;

import lombok.NoArgsConstructor;

import javax.persistence.*;


public enum Equipment {

    e1("Leather seats"),
    e2("Sunroof"),
    e3("Heated seats"),
    e4("Backup camera"),
    e5("Navigation system"),
    e6("Bluetooth"),
    e7("Remote start"),
    e8("Blind spot monitoring"),
    e9("Third-row seating"),
    e10("360-Degree Camera System"),
    e11("Evasive Steering"),
    e12("Pre-Safe Nudging"),
    e13("Launch Gear"),
    e14("Sound Enhancement"),
    e15("Home Assist Device Connectivity"),
    e16("Smart Trailer Features"),
    e17("Track Pace"),
    e18("Smart Suspension"),
    e19("Head-Up Display"),
    e20("Keyless Entry"),
    e21("Power Tailgate"),
    e22("Fast USB Charging Outlets"),
    e23("WiFi Hotspot"),
    e24("Rear Entertainment Systems"),
    e25("Auto-Dimming Mirrors"),
    e26("Multizone Climate System"),
    e27("Heated Steering Wheel"),
    e28("Power Driver’s Seat With Adjustable Lumbar Support");

    private final String name;

    Equipment(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public static Equipment fromString(String equipment) {
        for (Equipment b : Equipment.values())
            if (b.name.equalsIgnoreCase(equipment))
                return b;
        return null;
    }
}
