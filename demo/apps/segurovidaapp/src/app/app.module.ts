import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA, DoBootstrap, Injector } from '@angular/core';

import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { createCustomElement } from '@angular/elements';

import { DemologinModule} from '@demo/demologin';


@NgModule({
  declarations: [AppComponent],
  entryComponents: [AppComponent],
  imports: [
    BrowserModule,
    DemologinModule,
    RouterModule.forRoot([], { initialNavigation: 'enabled' }),
  ],
  schemas:[CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule implements DoBootstrap {

  constructor( private injector: Injector){

    const wc = createCustomElement( AppComponent, { injector: this.injector});
    customElements.define('mf-login', wc);
  }

  ngDoBootstrap(){};
}
