import { FC, useState } from "react";

import {
    Button,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField
} from "@mui/material";
import { useQueryClient, useMutation, UseMutationResult } from "@tanstack/react-query";

import { User } from "../types";
import { insertUser } from "../QueryFunctions/UserQueryFunctions";

interface Props {
    open: boolean;
    handClose: () => void;
}

export const AddUserDialog: FC<Props> = ({
    open, handClose
}) => {

    const queryClient = useQueryClient();

    const [userID, setUserID] = useState(0);

    const [firstName, setFirstName] = useState("");

    const [lastName, setLastName] = useState("");

    const [email, setEmail] = useState("");

    const onUserIdChange = (event: React.ChangeEvent<{ value: string }>) => {
        setUserID(event.target.value as unknown as number);
    }

    const onFirstNameChange = (event: React.ChangeEvent<{ value: string }>) => {
        setFirstName(event.target.value);
    }

    const onLastNameChange = (event: React.ChangeEvent<{ value: string }>) => {
        setLastName(event.target.value);
    }

    const onEmailChange = (event: React.ChangeEvent<{ value: string }>) => {
        setEmail(event.target.value);
    }

    const mutation: UseMutationResult<string, Error, User, unknown> = useMutation(insertUser, {
        onError: (error, variables, context) => {
            console.log("Error: " + error.message);
        },
        onSuccess: (data,variables, context) => {
            console.log(data);
            queryClient.invalidateQueries();
            handClose();
        }
    }) 

    const onClick = () => {
        const user: User = { userID, firstName, lastName, email };
        mutation.mutate(user);
    }

    return (
        <Dialog open={open} onClose={handClose}>
            <DialogTitle> Add User </DialogTitle>
            <DialogContent>
                <p>
                    UserID: <TextField sx={{width: 500}} value={userID} onChange={onUserIdChange} />
                </p>
                <p> 
                    FirstName: <TextField sx={{width: 500}} value={firstName} onChange={onFirstNameChange} />
                </p>

                <p> 
                    Last Name: <TextField sx={{width: 500}} value={lastName} onChange={onLastNameChange} />
                </p>

                <p> 
                    Email: <TextField sx={{width: 500}} value={email} onChange={onEmailChange} />
                </p>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClick}> Add </Button>
            </DialogActions>
        </Dialog>
    );
}