const menus = [
    {
        text: '비회원 메뉴',
        auth: [],
        items: [
            { text: '상품 검색', location: '/' }
        ]
    },
    {
        text: '마이 페이지',
        auth: ['user', 'admin'],
        items: [
            { text: '내 정보 조회',  location: '/my-information' },
            { text: '구매 이력 조회',  location: '/my-purchase-log' },
        ]
    },
    {
        text: '상품 관리',
        auth: ['admin'],
        items: [
            { text: '상품 등록', location: '/add-new-product' },
            { text: '판매 완료 상품 관리', location: '/purchase-management' },
        ]
    },
    {
        text: '회원 관리',
        auth: ['admin'],
        items: [
            { text: '가입 회원 정보 조회', location: '/user-management' },
        ]
    }
]


const viewNavbar = async (navBarId) => {
    const loginUser = await getLoginUser();
    const $navBar = $(`#${navBarId}`);

    const html = `<nav class="navbar bg-body-tertiary fixed-top">
                            <div class="container-fluid">
                                <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                                        aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>
                                <a class="navbar-brand" href="/">
                                    <img src="/images/ShoppingMallLogo.png" alt="" width="103.04" height="44"/>
                                </a>
                                <div class="offcanvas offcanvas-start" tabIndex="-1" id="offcanvasNavbar"
                                     aria-labelledby="offcanvasNavbarLabel">
                                    <div class="offcanvas-header">
                                        <a class="navbar-brand" href="/">
                                            <img src="/images/ShoppingMallLogo.png" alt="" width="103.04" height="44"/>
                                        </a>
                                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                                    </div>
                                    <div class="offcanvas-body">
                                        <ul id="navbar" class="navbar-nav justify-content-end flex-grow-1 pe-3">
                                            ${getNavItemHtml(loginUser)}
                                        </ul>
                                    </div>
                                </div>
                                ${getLoginBtnHtml(loginUser)}
                            </div>
                        </nav>`;

    $navBar.html(html);
};

const getNavItemHtml = (loginUser) => {
    let html = '';

    for(let menu of menus) {
        //로그인 했고 권한이 있는 메뉴 혹은 로그인 안했을 떄 비회원 메뉴
        if ((loginUser && menu.auth.includes(loginUser.role))
            || !loginUser && menu.auth.length === 0) {
            html += '<li class="nav-item">';
            html += `<p>${menu.text}</p>`;
            for(let item of menu.items) {
                html += `<a class="nav-link " aria-current="page" href="${item.location}">${item.text}</a>`
            }
        }
    }

    return html;
};

const getLoginBtnHtml = (loginUser) => {
    if (loginUser) {
        return `<button type="button" id="login-btn"
                        class="justify-content-start align-items-center d-flex btn btn-outline-dark"
                        onClick="logout()">
                    <i class="bi bi-door-closed fs-5 me-3"></i>
                    <a class="nav-link active" aria-current="page">로그아웃</a>
                </button>`
    } else {
        return `<button type="button" id="login-btn"
                        class="justify-content-start align-items-center d-flex btn btn-outline-dark"
                        onClick="location.href='/login'">
                    <i class="bi bi-door-closed fs-5 me-3"></i>
                    <a class="nav-link active" aria-current="page" href="/login">로그인</a>
                </button>`;
    }
}