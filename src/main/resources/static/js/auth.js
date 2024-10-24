const LOGIN_USER_KEY = "LOGIN_USER";

const getLoginUser = () => {
    return new Promise((resolve, reject) => {
        if (sessionStorage.getItem('loginUser')) {
            resolve(sessionStorage.getItem(JSON.parse(LOGIN_USER_KEY)));
        } else {
            sendGetAjax('/api/users/login-user', (res) => {
                console.log(">>>>>>>>>>>>>>>>>>", res);
                if (res) {
                    sessionStorage.setItem(LOGIN_USER_KEY, JSON.stringify(res));
                }
                return resolve(res);
            }, err => {
                return reject(err);
            })
        }
    })
};

const logout = () => {
    return new Promise((resolve, reject) => {
        sendPostAjax("/api/users/logout", null, res => {
            sessionStorage.removeItem(LOGIN_USER_KEY);
            alert('로그아웃 되었습니다.');
            window.location.href = '/';
        }, err => {
            return reject(err);
        })
    });
}
