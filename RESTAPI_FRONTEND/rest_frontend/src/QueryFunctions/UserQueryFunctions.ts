import { User } from "../types";

export async function fetchUser (): Promise<User[]> {
    const res = await fetch("http://localhost:8080/RestAPI/users/");
    if(!res.ok) {
        throw new Error(res.statusText);
    }
    
    const users: User[] = await res.json();
    return users;
}

export async function insertUser (user: User) {
    const res = await fetch(
                "http://localhost:8080/RestAPI/users/",
                {
                    method: "POST",
                    headers: {
                      "Content-Type": "application/json",
                      // 'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: JSON.stringify(user), // body data type must match "Content-Type" header
                }
            );
    if(!res.ok) {
        throw new Error(res.statusText);
    }

    return "Successful";
}