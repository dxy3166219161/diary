import {createRouter, createWebHashHistory} from 'vue-router'
import Login from "../views/Login";
import Test from "../components/Test";
import Operation from "@/views/Operation";

const routes = [
    {
        path: '/',
        name: 'Login',
        component: Login
    }, {
        path: '/operation',
        name: 'Operation',
        component: Operation
    },
    {
        path: '/test',
        name: 'Test',
        component: Test
    }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router
