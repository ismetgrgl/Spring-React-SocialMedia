import { useAppSelector } from "./store";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./pages/login/Login";
import Home from "./pages/home/Home";
import UserUpdate from "./components/molecules/UserUpdate";


function RouterPage() {
	
	const isLogin = useAppSelector((state) => state.auth.isAuth);

	return (
		<BrowserRouter>
			<Routes>
				<Route path="/" element={isLogin ? <Home /> : <Login />} />
				<Route path="/login" element={<Login />} />
				<Route path="/update" element={<UserUpdate />} />
			</Routes>
		</BrowserRouter>
	);
}

export default RouterPage;
