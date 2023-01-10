import jwt_decode from "jwt-decode";

class AuthenticationService {
    getRoles() {
        let token = localStorage.getItem("token");
        if (token !== null) {
            const roles = jwt_decode(JSON.stringify(token)).roles;
            return roles;
        }
        return [];
    }

    isTokenExpired(token) {
        if (token !== null) {
            const exp = jwt_decode(JSON.stringify(token)).exp;
            const date = new Date();
            const currentTime = date.getTime() / 1000;
            if (currentTime > exp) {
                return true;
            }
            return false;
        }
        return true;
    }

    getUserId() {
        const token = localStorage.getItem("token");
        if (token !== null) {
            const userId = jwt_decode(JSON.stringify(token)).userId;
            return userId;
        }
        return null;
    }
}

export default new AuthenticationService();