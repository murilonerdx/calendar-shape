import '@vaadin/vaadin-form-layout';
import '@vaadin/vaadin-grid';
import '@vaadin/vaadin-grid/vaadin-grid-column';
import '@vaadin/vaadin-ordered-layout/vaadin-horizontal-layout';
import '@vaadin/vaadin-split-layout';
import { customElement, html, LitElement } from 'lit-element';
import '@vaadin/vaadin-text-field';
import '@vaadin/vaadin-combo-box/src/vaadin-combo-box.js';
import '@vaadin/vaadin-date-picker/src/vaadin-date-picker.js';
import '@vaadin/vaadin-button';

@customElement('listardatas-view')
export class ListardatasView extends LitElement {
  createRenderRoot() {
    // Do not use a shadow root
    return this;
  }

  render() {
    return html`
<vaadin-split-layout style="width: 100%; height: 100%;">
 <div style="flex-grow:1;width:100%;" id="grid-wrapper" theme="">
  <vaadin-grid id="grid"></vaadin-grid>
 </div>
 <div style="width:400px;display:flex;flex-direction:column;">
  <div style="padding:var(--lumo-space-l);flex-grow:1;">
   <vaadin-form-layout>
    <vaadin-text-field label="Nome do evento" id="nameEvent"></vaadin-text-field>
    <vaadin-text-field label="Descrição do evento" id="descriptionEvent"></vaadin-text-field>
    <vaadin-date-picker label="Data do evento" placeholder="Data do evento" id="dateEvent" show-week-numbers></vaadin-date-picker>
    <vaadin-combo-box id="importEvent"></vaadin-combo-box>
    <vaadin-text-field label="Tipo do evento" id="typeEvent"></vaadin-text-field>
   </vaadin-form-layout>
  </div>
  <vaadin-horizontal-layout style="flex-wrap:wrap;width:100%;background-color:var(--lumo-contrast-5pct);padding:var(--lumo-space-s) var(--lumo-space-l);" theme="spacing">
   <vaadin-button theme="primary" id="save">
     Salvar 
   </vaadin-button>
   <vaadin-button id="delete" theme="primary error">
     Deletar 
   </vaadin-button>
   <vaadin-button theme="tertiary" slot="" id="cancel">
     Cancelar 
   </vaadin-button>
  </vaadin-horizontal-layout>
 </div>
</vaadin-split-layout>
`;
  }
}
