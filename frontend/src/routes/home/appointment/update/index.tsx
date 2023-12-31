import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { toast } from "sonner";

import AppointmentForm from "@/components/forms/AppointmentForm";
import { Button } from "@/components/ui/button";
import { API_URL } from "@/config/config";
import UpdateAppointmentForm from "@/components/forms/UpdateAppointmentForm";

interface Appointment {
  appointmentId: number;
  appointmentDate: string;
  appointmentTime: string;
  patient: {
    userId: number;
    firstName: string;
    lastName: string;
    email: string;
    mobile: number;
    userType: string;
    address: string;
    dob: string;
  };
  dentist: {
    userId: number;
  };
  regFeeStatus: string;
}

const UpdateAppointment = () => {
  const { id } = useParams();

  return (
    <div className="w-screen px-28 space-y-10">
      <div className="flex justify-between items-center">
        <h2 className="text-center text-2xl font-semibold">Update Appointment</h2>
        <Link to="/appointments" className="">
          <Button className="uppercase">Back</Button>
        </Link>
      </div>
      <div>
        <UpdateAppointmentForm id={id} />
      </div>
    </div>
  );
};

export default UpdateAppointment;
