import { useQueryClient, useQuery, UseQueryResult } from "@tanstack/react-query";
import { FC, useState } from "react";
import { Button } from "@mui/material";
import { fetchUser } from "../QueryFunctions/UserQueryFunctions";
import { User } from "../types";
import { AddUserDialog } from "./AddUserDialog";

interface Props {}

export const UserList: FC<Props> = () => {

    const [openDialog, setOpenDialog] = useState(false);

    const queryClient = useQueryClient();

    const query: UseQueryResult<User[], Error> = useQuery({ queryKey: ['users'], queryFn: fetchUser });

    const handleDialogClose = () => {
        setOpenDialog(false);
    }

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
                                <p> First Name: {user.firstName} </p>
                                <p> Last Name: {user.lastName} </p>
                                <p> email: {user.email} </p>
                            </li>
                        );
                    })
                }
            </ul>
            <Button onClick={ () => setOpenDialog(true) }> Add New User </Button>

            <AddUserDialog open={openDialog} handClose={handleDialogClose} />
        </div>
    );
}