import React from 'react';
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import logo from './logo.svg';
import './App.css';
import { UserList } from './components';

const queryClient = new QueryClient();

function App() {
  return (
    <QueryClientProvider client={queryClient}>
       <UserList />
    </QueryClientProvider>
  );
}

export default App;
