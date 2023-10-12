import { useQueryClient, useQuery, UseQueryResult } from "@tanstack/react-query";
import { FC } from "react";
import { fetchUser } from "../QueryFunctions/UserQueryFunctions";
import { User } from "../types";

interface Props {}

export const UserList: FC<Props> = () => {
    const queryClient = useQueryClient();

    const query: UseQueryResult<User[], Error> = useQuery({ queryKey: ['users'], queryFn: fetchUser });

    if(query.isLoading) {
        return <div> Loading... </div>
    }

    if(query.isError) {
        console.log(query.error.message);
        return <div> Error: {query.error.message} </div>;
    }


    return (
        <div>
            <ul>
                {
                    query.data?.map((user, index) => {
                        return ( 
                            <li key={`${user.userID}-${index}`}>
                                <p> userID: {user.userID} </p>
                                <p> First Name: {user.fisrtName} </p>
                                <p> Last Name: {user.lastName} </p>
                                <p> email: {user.email} </p>
                            </li>
                        );
                    })
                }
            </ul>
        </div>
    );
}