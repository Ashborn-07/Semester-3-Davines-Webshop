import jwt_decode from "jwt-decode";

class AuthenticationService {
    getRoles(token) {
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
}

export default new AuthenticationService();