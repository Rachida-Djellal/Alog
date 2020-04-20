package Controller;

import models.Appointment;

import java.io.IOException;

public interface Printer {

     void print(Appointment appointment) throws IOException;
}
