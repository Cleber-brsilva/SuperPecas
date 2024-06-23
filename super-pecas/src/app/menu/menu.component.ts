import { Component } from '@angular/core';
import { MatListModule } from '@angular/material/list';

interface MenuItem {
  path: string;
  label: string;
}

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [MatListModule],
  templateUrl: './menu.component.html',
  styles: ``
})
export class MenuComponent {
  menuItems: Array<MenuItem> = [
    {
      path: '/',
      label: 'Home'
    },
    {
      path: '/carros',
      label: 'Carros'
    },
    {
      path: '/pecas',
      label: 'Peças'
    }
  ]
}
