import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import{API_URL} from'../configUrl';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly apiUrl = API_URL;
  private readonly sendGridApiKey = SEND_GRID;

  constructor(private http: HttpClient) {}

  register(user: any) {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  

  sendVerificationEmail(userEmail: string, verificationToken: string) {
    const url = `${this.apiUrl}/verify-email/${verificationToken}`;

    const message = {
      to: userEmail,
      from: 'francismethery@gmail.com',
      subject: 'Verify your email address',
      text: `Please click on the following link to verify your email address: ${url}`,
      html: `Please click on the following link to verify your email address: <a href="${url}">${url}</a>`,
    };

    return this.http.post(`${this.apiUrl}/send-email`, message, {
      headers: {
        Authorization: `Bearer ${this.sendGridApiKey}`,
      },
    });
  }
}
