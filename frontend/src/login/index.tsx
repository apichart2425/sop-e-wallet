import { Button, Form, Icon, Input, Avatar } from 'antd'
import { FormComponentProps } from 'antd/lib/form/Form'
import { observer, useObservable } from 'mobx-react-lite'
import React, { useCallback, useEffect } from 'react'
import styled from 'styled-components'

import logo from '../logo.jpg'
import UserStore from '../stores/user'

const FormItem = Form.Item

const Container = styled.div`
  width: 500px;
  background: white;
  border-radius: 5px;
  box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);
  padding: 10px 20px;
  padding-top: 30px;
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
`

const LoginButton = styled(Button)`
  width: 100%;
`

const Title = styled.h1`
  margin: 0.5em 0;
`

interface ILoginForm {
  username: string
  password: string
}

const LoginPage: React.FC = () => {
  const userStore = useObservable(UserStore)

  useEffect(() => {
    userStore.checkAuthentication()
  }, [userStore])

  const handleSubmit = useCallback(
    (form: ILoginForm) => {
      userStore.authenticate(form.username, form.password)
    },
    [userStore]
  )

  // Login Form Template
  type LoginFormProps = FormComponentProps
  function LoginFormImpl(props: LoginFormProps): JSX.Element {
    const { form } = props
    const { getFieldDecorator, validateFields } = form

    const onSubmit = (event: React.FormEvent) => {
      event.preventDefault()

      validateFields((err, values) => {
        if (!err) {
          handleSubmit(values)
        }
      })
    }

    return (
      <Form onSubmit={onSubmit}>
        <FormItem>
          {getFieldDecorator('username', {
            rules: [{ required: true, message: 'Please input your username!' }]
          })(
            <Input
              placeholder="Username"
              prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
            />
          )}
        </FormItem>

        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Please input your password!' }]
          })(
            <Input
              placeholder="Password"
              type="password"
              prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
            />
          )}
        </FormItem>

        <FormItem>
          <LoginButton type="primary" htmlType="submit">
            <Icon type="login" style={{ color: 'white' }} /> Login
          </LoginButton>
        </FormItem>
      </Form>
    )
  }

  const LoginForm = Form.create()(LoginFormImpl)

  return (
    <>
      <Container>
        <Avatar src={logo} size={250}></Avatar>
        <Title>Pachara E-Wallet</Title>
        <LoginForm />
      </Container>
    </>
  )
}

export default observer(LoginPage)