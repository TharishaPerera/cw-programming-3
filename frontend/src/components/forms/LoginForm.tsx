import { z } from "zod";
import { Button } from "../ui/button";
import { Input } from "../ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormMessage,
} from "../ui/form";
import { API_URL } from "@/config/config";
import { toast } from "sonner";

const LoginSchema = z.object({
  email: z.string().min(1, "Please enter your email address").email(),
  password: z.string().min(1, "Please enter your password"),
});

const LoginForm = () => {
  const form = useForm<z.infer<typeof LoginSchema>>({
    resolver: zodResolver(LoginSchema),
    defaultValues: {
      email: "",
      password: "",
    },
  });

  async function onSubmit(values: z.infer<typeof LoginSchema>) {
    const loginResponse = await fetch(API_URL + `/auth/login?email=${values.email}&password=${values.password}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
    });
    const login = await loginResponse.json()
    if (!login) {
      toast.error("Invalid credentials, please check and try again.");
    } else {
      window.location.href = "/home";
    }
  }

  return (
    <>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
          <FormField
            control={form.control}
            name="email"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Input placeholder="username@example.com" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            control={form.control}
            name="password"
            render={({ field }) => (
              <FormItem>
                <FormControl>
                  <Input type="password" placeholder="********" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <Button type="submit" className="w-full">Submit</Button>
        </form>
      </Form>
    </>
  );
};

export default LoginForm;
