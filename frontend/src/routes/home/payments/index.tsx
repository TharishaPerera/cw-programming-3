import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "sonner";

import { Button } from "@/components/ui/button";
import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { API_URL } from "@/config/config";
import { format } from "@/lib/utils";

interface Data {
  paymentId: number;
  invoice: {
    invoiceId: number;
    dateIssued: string;
    treatment: {
      treatmentId: number;
      appointment: {
        appointmentId: number
      }
    }
  };
  amount: number
  dateTime: string
  paymentMethod: string
  paymentStatus: string
}

const Payment = () => {
  const [data, setData] = useState<Data[]>([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const paymentResponse = await fetch(API_URL + "/payments");
        if (!paymentResponse.ok) {
          toast.error("Something went wrong");
        }

        const payments = await paymentResponse.json();
        setData(payments);
      } catch (error) {
        console.log(error);
        toast.error("Error occurred when data fetching");
      }
    };

    fetchData();
  }, []);

  return (
    <div className="w-screen px-28 space-y-10">
      <div className="flex justify-between items-center">
        <h2 className="text-center text-2xl font-semibold">Payments</h2>
        <div className="space-x-2">
          <Link to="/home">
            <Button className="uppercase">Home</Button>
          </Link>
        </div>
      </div>
      <div>
        <Table>
          <TableCaption>A list of Invoices.</TableCaption>
          <TableHeader>
            <TableRow>
              <TableHead>Payment Id</TableHead>
              <TableHead>Invoice Id</TableHead>
              <TableHead>Appointment Id</TableHead>
              <TableHead>Payment Date</TableHead>
              <TableHead>Payment Method</TableHead>
              <TableHead>Total Amount</TableHead>
              <TableHead>Payment Status</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data?.map((item, index) => (
              <TableRow key={index}>
                <TableCell>{item?.paymentId}</TableCell>
                <TableCell>{item?.invoice.invoiceId}</TableCell>
                <TableCell>{item?.invoice?.treatment?.appointment.appointmentId}</TableCell>
                <TableCell>{format(new Date(item?.dateTime))}</TableCell>
                <TableCell>{item?.paymentMethod}</TableCell>
                <TableCell>{item?.amount}</TableCell>
                <TableCell>{item?.paymentStatus}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default Payment;
