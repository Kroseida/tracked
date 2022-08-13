import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/application.vue'),
    children: [],
  },
  {
    path: '/installation/',
    component: () => import('layouts/installation.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/installation/installationView.vue')
      },
    ],
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
