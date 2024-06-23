import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CarrosComponent } from './carros/carros.component';
import { PecasComponent } from './pecas/pecas.component';

export const routes: Routes = [
  {
    path: 'carros',
    component: CarrosComponent
  },
  {
    path: 'pecas',
    component: PecasComponent
  }
];
